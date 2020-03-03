package de.jakob.util;

public class Pair<L,R> {

    private final L left;
    private final R right;

    public Pair(L left, R right) {
        assert left != null;
        assert right != null;

        this.left = left;
        this.right = right;
    }

    public L getL() { return left; }
    public R getR() { return right; }

    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair<?,?> other = (Pair<?,?>) o;
        return this.left.equals(other.getL()) && this.right.equals(other.getR());
    }
}