package com.bitarcher.aeFun.widgetToolkit.widget;

/*
 * Copyright (c) 2015.
 * Michel Strasser
 * bitarcher.com
 */

import com.bitarcher.aeFun.interfaces.gui.theme.ITheme;
import com.bitarcher.aeFun.interfaces.gui.theme.context.INoneContext;
import com.bitarcher.aeFun.interfaces.gui.widgets.IWidget;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.ITable;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.ENoFreeTableCellFound;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.ETableCellNotEmpty;
import com.bitarcher.aeFun.interfaces.gui.widgets.Containers.Other.ISpaceUsage;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.Container;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.IWidgetTableCellsConsumption;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.PercentSpaceUsage;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.ScalarComputerBySpaceUsage;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.ForTable.TableColumn;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.ForTable.TableRow;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.ForTable.TableCell;
import com.bitarcher.aeFun.widgetToolkit.widget.Tools.Containers.ForTable.WidgetTableCellsConsumption;

import org.andengine.util.IMatcher;
import org.andengine.util.adt.list.SmartList;

/**
 * Created by michel on 04/03/15.
 */
public class Table extends Container<INoneContext> implements ITable {

    boolean shouldFixedSpaceUsageBeResizedOnResize = false;
    SmartList<TableColumn> tableColumns = new SmartList<>();
    SmartList<TableRow> tableRows = new SmartList<>();

    // outer list contains rows, inner list contains cell for current row
    SmartList<SmartList<TableCell>> tableCells = new SmartList<>();
    SmartList<WidgetTableCellsConsumption> widgetTableCellsConsumptions = new SmartList<>();

    public Table(ITheme theme, float pX, float pY, float pWidth, float pHeight) {
        super(theme, pX, pY, pWidth, pHeight);
    }

    @Override
    public boolean isShouldFixedSpaceUsageBeResizedOnResize() {
        return shouldFixedSpaceUsageBeResizedOnResize;
    }

    @Override
    public void setShouldFixedSpaceUsageBeResizedOnResize(boolean shouldFixedSpaceUsageBeResizedOnResize) {
        this.shouldFixedSpaceUsageBeResizedOnResize = shouldFixedSpaceUsageBeResizedOnResize;
    }


    @Override
    protected void doAttachWidget(IWidget widget) {
        TableCell foundEmptyTableCell = null;

        for(SmartList<TableCell> rowTableCell : tableCells)
        {
            for(TableCell tc : rowTableCell)
            {
                if(tc.getWidgetTableCellsConsumption() == null)
                {
                    foundEmptyTableCell = tc;
                    break;
                }
            }

            if(foundEmptyTableCell != null)
            {
                break;
            }
        }

        if(foundEmptyTableCell != null)
        {
            this.attachChild(widget, foundEmptyTableCell.getColumnNum(), foundEmptyTableCell.getRowNum());
            /*WidgetTableCellsConsumption widgetTableCellsConsumption = new WidgetTableCellsConsumption(widget, foundEmptyTableCell.getColumnNum(), foundEmptyTableCell.getRowNum(), 1, 1);
            foundEmptyTableCell.setWidgetTableCellsConsumption(widgetTableCellsConsumption);
            this.widgetTableCellsConsumptions.add(widgetTableCellsConsumption);
            this.recomputeWidgetSizeAndPositionByWidgetTableCellConsumption(widgetTableCellsConsumption);*/
        }
        else
        {
            throw new ENoFreeTableCellFound(this, widget);
        }
    }

    void _addColumn(ISpaceUsage spaceUsage) {
        this.tableColumns.addLast(new TableColumn(spaceUsage));
    }

    @Override
    public void addColumn(ISpaceUsage spaceUsage) {
        this._addColumn(spaceUsage);

        this.recomputeColumnsSizeAndPosition();
        this.recomputeCells();
        this.recomputeChildrenWidgetsSizeAndPosition();
    }

    void _addRow(ISpaceUsage spaceUsage)
    {
        this.tableRows.addLast(new TableRow(spaceUsage));
    }

    @Override
    public void addRow(ISpaceUsage spaceUsage) {
        this._addRow(spaceUsage);

        this.recomputeRowsSizeAndPosition();
        this.recomputeCells();
        this.recomputeChildrenWidgetsSizeAndPosition();
    }

    @Override
    protected void recomputeAllContainedPositionAndSize() {
        this.recomputeColumnsSizeAndPosition();
        this.recomputeRowsSizeAndPosition();
        this.recomputeCells();
        this.recomputeChildrenWidgetsSizeAndPosition();
    }

    @Override
    public void addHomogeneousColumnsAndRows(int numOfColumns, int numOfRows) {
        this.addHomogeneousColumnsAndRows(numOfColumns, numOfRows, 0);
    }

    @Override
    public void addHomogeneousColumnsAndRows(int numOfColumns, int numOfRows, float margin) {
        for(int i= 0 ; i < numOfColumns ; i++)
        {
            this.addColumn(new PercentSpaceUsage(margin, 100));
        }

        this.recomputeColumnsSizeAndPosition();

        for(int i= 0 ; i < numOfRows ; i++)
        {
            this.addRow(new PercentSpaceUsage(margin, 100));
        }

        this.recomputeRowsSizeAndPosition();

        this.recomputeCells();
        this.recomputeChildrenWidgetsSizeAndPosition();
    }

    void setAllCoveringTableCellWithWidget(int left, int top, int columnsSpan, int rowSpan, IWidgetTableCellsConsumption widgetTableCellsConsumption)
    {
        for(int i = left ; i < left + columnsSpan  ; i++) {


            for (int j = top; j < top + rowSpan ; j++) {

                TableCell tableCell = this.getTableCell(i, j);

                tableCell.setWidgetTableCellsConsumption(widgetTableCellsConsumption);
            }
        }
    }

    SmartList<TableCell> findAllCoveringTableCellByExistingWidget(int left, int top, int columnsSpan, int rowSpan)
    {
        SmartList<TableCell> retval = new SmartList<>();

        for(int i = left ; i < left + columnsSpan  ; i++)
        {


            for(int j = top ; j < top + rowSpan ; j++)
            {

                TableCell tableCell = this.getTableCell(i, j);
                if(tableCell.getWidgetTableCellsConsumption() != null)
                {
                    retval.add(tableCell);
                }
            }
        }

        return retval;
    }

    TableCell findFirstCoveringTableCellByExistingWidget(int left, int top, int columnsSpan, int rowSpan)
    {
        TableCell retval = null;

        SmartList<TableCell> list = this.findAllCoveringTableCellByExistingWidget(left, top, columnsSpan, rowSpan);

        if(!list.isEmpty())
        {
            retval = list.getFirst();
        }

        return retval;
    }

    @Override
    public void attachChild(IWidget widget, int left, int top, int columnsSpan, int rowSpan) {

        TableCell foundCoveredTableCell = this.findFirstCoveringTableCellByExistingWidget(left, top, columnsSpan, rowSpan);

        if(foundCoveredTableCell != null)
        {
            throw new ETableCellNotEmpty(this, widget, left, top, columnsSpan, rowSpan, foundCoveredTableCell.getColumnNum(), foundCoveredTableCell.getRowNum());
        }

        WidgetTableCellsConsumption widgetTableCellsConsumption = new WidgetTableCellsConsumption(widget, left, top, columnsSpan, rowSpan);
        this.widgetTableCellsConsumptions.add(widgetTableCellsConsumption);

        this.entityAttachChild(widget);
        this.setAllCoveringTableCellWithWidget(left, top, columnsSpan, rowSpan, widgetTableCellsConsumption);

        this.recomputeWidgetSizeAndPositionByWidgetTableCellConsumption(widgetTableCellsConsumption);
    }

    @Override
    public void attachChild(IWidget widget, int left, int top) {
        this.attachChild(widget, left, top, 1, 1);
    }

    @Override
    public void doDetachChild(final IWidget widget)
    {
        this.widgetTableCellsConsumptions.remove(new IMatcher<WidgetTableCellsConsumption>() {
            @Override
            public boolean matches(WidgetTableCellsConsumption pObject) {
                return pObject.getWidget() == widget;
            }
        });

        this.entityDetachChild(widget);
    }

    @Override
    public void resetOriginalWidthAndHeight(float originalWidth, float originalHeight) {
        this.setOriginalWidth(originalWidth);
        this.setOriginalHeight(originalHeight);
    }

    private void recomputeColumnsSizeAndPosition()
    {
        ScalarComputerBySpaceUsage scalarComputerBySpaceUsage = new ScalarComputerBySpaceUsage();

        scalarComputerBySpaceUsage.compute(this.tableColumns, this.getWidth(), this.getPadding(), this.isShouldFixedSpaceUsageBeResizedOnResize());
    }

    private void recomputeRowsSizeAndPosition()
    {
        ScalarComputerBySpaceUsage scalarComputerBySpaceUsage = new ScalarComputerBySpaceUsage();

        scalarComputerBySpaceUsage.compute(this.tableRows, this.getHeight(), this.getPadding(), this.isShouldFixedSpaceUsageBeResizedOnResize());
    }

    private void recomputeCells()
    {
        this.tableCells.clear();

        int indexTableRow = 0;
        float startX = this.getPadding();
        float startY = this.getHeight() - 2 * this.getPadding();


        float currentY = startY;


        // table cell anchor center is up left corner, so it defers from box on this point
        for(TableRow tableRow: this.tableRows)
        {
            float currentX = startX;
            int indexTableColumn = 0;
            SmartList<TableCell> currentRowTableCells = new SmartList<>();
            this.tableCells.add(currentRowTableCells);
            float ch = tableRow.getScalarWithoutTwoMargin();
            float cy = currentY + tableRow.getSpaceUsage().getMargin();

            for(TableColumn tableColumn:this.tableColumns) {

                TableCell tableCell = new TableCell(indexTableColumn, indexTableRow, tableColumn, tableRow);

                currentRowTableCells.add(tableCell);

                float cw = tableColumn.getScalarWithoutTwoMargin();
                float cx = currentX + tableColumn.getSpaceUsage().getMargin();

                tableCell.setPosition(cx, cy);
                tableCell.setSize(cw, ch);

                currentX += tableColumn.getScalar();
                indexTableColumn++;
            }

            currentY -= tableRow.getScalar();
            indexTableRow++;
        }

        this.setTableCellsWidgetReference();
    }

    // precondition : all widget pointers of cells are set to empty
    void setTableCellsWidgetReference()
    {
        for(WidgetTableCellsConsumption widgetTableCellsConsumption :this.widgetTableCellsConsumptions)
        {
            for(int i = 0 ; i < widgetTableCellsConsumption.getColumnSpan() ; i++)
            {
                int column = widgetTableCellsConsumption.getLeft() + i;

                for(int j = 0 ; j < widgetTableCellsConsumption.getRowSpan(); j++)
                {
                    int row = widgetTableCellsConsumption.getTop() + j;

                    TableCell tableCell = this.getTableCell(column, row);
                    tableCell.setWidgetTableCellsConsumption((widgetTableCellsConsumption));
                }
            }
        }
    }

    @Override
    public IWidget getChildAt(int column, int row)
    {
        IWidget retval = null;

        TableCell cell = this.getTableCell(column, row);

        retval = cell.getWidgetTableCellsConsumption().getWidget();

        return retval;
    }

    protected TableCell getTableCell(int column, int row)
    {
        TableCell retval = null;

        retval = this.tableCells.get(row).get(column);

        return retval;
    }

    private void recomputeChildrenWidgetsSizeAndPosition()
    {
        for(WidgetTableCellsConsumption widgetTableCellsConsumption : this.widgetTableCellsConsumptions)
        {
            this.recomputeWidgetSizeAndPositionByWidgetTableCellConsumption(widgetTableCellsConsumption);
        }

        this.raiseChildrenPositionRecomputed();
    }

    private void recomputeWidgetSizeAndPositionByWidgetTableCellConsumption(WidgetTableCellsConsumption widgetTableCellsConsumption)
    {
        TableCell leftTopCell = this.getTableCell(widgetTableCellsConsumption.getLeft(), widgetTableCellsConsumption.getTop());
        TableCell rightBottomCell = this.getTableCell(widgetTableCellsConsumption.getLeft() + widgetTableCellsConsumption.getColumnSpan() - 1, widgetTableCellsConsumption.getTop() + widgetTableCellsConsumption.getRowSpan() - 1);

        float startX = leftTopCell.getX();
        float startY = leftTopCell.getY();
        float endX = rightBottomCell.getX() + rightBottomCell.getWidth();
        float endY = rightBottomCell.getY() - rightBottomCell.getHeight();
        float width = endX - startX;
        float height =   startY - endY;

        // anchor center, remember !
        float widgetX = (startX + endX)/ 2;
        float widgetY = (startY + endY) / 2;

        widgetTableCellsConsumption.getWidget().setPosition(widgetX, widgetY);
        widgetTableCellsConsumption.getWidget().setSize(width, height);
    }

    @Override
    public int getNumOfColumns() {
        return this.tableColumns.size();
    }

    @Override
    public int getNumOfRows() {
        return this.tableRows.size();
    }
}

