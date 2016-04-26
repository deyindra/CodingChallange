package com.ea.challange.model;

/**
 * @author idey
 * Class represent the row and column index of the individual words
 */
public class RowColIndex {
    private long rowIndex;
    private long colIndex;

    public RowColIndex(long rowIndex, long colIndex) {
        this.rowIndex = rowIndex;
        this.colIndex = colIndex;
    }

    public long getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(long rowIndex) {
        this.rowIndex = rowIndex;
    }

    public long getColIndex() {
        return colIndex;
    }

    public void setColIndex(long colIndex) {
        this.colIndex = colIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RowColIndex that = (RowColIndex) o;

        if (rowIndex != that.rowIndex) return false;
        return colIndex == that.colIndex;

    }

    @Override
    public int hashCode() {
        int result = (int) (rowIndex ^ (rowIndex >>> 32));
        result = 31 * result + (int) (colIndex ^ (colIndex >>> 32));
        return result;
    }


    @Override
    public String toString() {
        return new StringBuilder("(")
                .append(rowIndex)
                .append(",")
                .append(colIndex).append(")").toString();
    }
}
