package cn.shop.base;

import java.util.List;

public class MutilTree<T>
{
    private List<MutilTree<T>> children = null;

    private T node = null;

    public T getNode()
    {
        return node;
    }

    public void setNode(T node)
    {
        this.node = node;
    }

    public void setChildren(List<MutilTree<T>> children)
    {
        this.children = children;
    }

    public List<MutilTree<T>> getChildren()
    {
        return children;
    }
}
