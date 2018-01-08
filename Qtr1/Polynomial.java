package com.LabTests.src.Qtr1;

import com.LabTests.src.Qtr1.ListNodeLinkedListLab;

import com.LabTests.src.Qtr1.ListNodeLinkedListLab.ListNode;

public interface Polynomial
{

    Polynomial plus(Polynomial p2);

    Polynomial minus(Polynomial p3);

    Polynomial differentiate();

    Polynomial multiply(Polynomial p2);

    double evaluate(int i);

    ListNode<Integer> getPolyLinkedList();

    void setPolyLinkedList(ListNode<Integer> x);

}
