package edu.umn.d.cs1632;

class DoubleValue extends Value {
    private final double val;

    public DoubleValue(double val) {
        this.val = val;
    }

    @Override
    public Double getVal() {
        return val;
    }

    @Override
    public String toString() {
        return Double.toString(val)+ "(D)";
    }
}