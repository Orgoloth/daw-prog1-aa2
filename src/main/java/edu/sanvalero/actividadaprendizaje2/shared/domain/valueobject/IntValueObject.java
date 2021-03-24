package edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject;

public abstract class IntValueObject {
    protected int value;

    public IntValueObject(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public boolean isBiggerThan(IntValueObject other) {
        return value() > other.value();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + value;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IntValueObject other = (IntValueObject) obj;
        if (value != other.value)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

}
