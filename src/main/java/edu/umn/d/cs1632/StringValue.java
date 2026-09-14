package edu.umn.d.cs1632;

class StringValue extends Value {
    private final String val;

    public StringValue(String val) {
        this.val = val;
    }

    @Override
    public String getVal() {
        return val;
    }

    @Override
    public String toString() {
        return val + "(S)";
    }
}