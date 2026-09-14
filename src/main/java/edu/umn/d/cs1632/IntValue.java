package edu.umn.d.cs1632;

class IntValue extends Value {
    private final int val;

    public IntValue(int val) {
        this.val = val;
    }

    @Override
    public Integer getVal() {
        return val;
    }

    @Override
    public String toString() {
        return Integer.toString(val) + "(I)";
    }
}
