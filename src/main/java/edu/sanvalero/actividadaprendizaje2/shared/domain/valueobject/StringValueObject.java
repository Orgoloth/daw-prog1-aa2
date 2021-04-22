package edu.sanvalero.actividadaprendizaje2.shared.domain.valueobject;

public abstract class StringValueObject {

    protected final String value;

    public StringValueObject(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public boolean contains(StringValueObject otherStringValueObject) {
        return value.toLowerCase().contains(otherStringValueObject.value().toLowerCase());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((value == null) ? 0 : value.hashCode());
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
        StringValueObject other = (StringValueObject) obj;
        if (value == null) {
            return other.value == null;
        } else {
            return value.equalsIgnoreCase(other.value);
        }
    }

    @Override
    public String toString() {
        return value;
    }

}
