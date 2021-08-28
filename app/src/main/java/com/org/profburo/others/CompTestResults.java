package com.org.profburo.others;

import com.org.profburo.network.responsesEntities.test.TestResults;

import java.util.Comparator;

public class CompTestResults implements Comparator<TestResults> {

    @Override
    public int compare(TestResults o1, TestResults o2) {
        if (o1.getBalls() > o2.getBalls())
        {
            return 1;
        }
        else if (o1.getBalls() == o2.getBalls())
        {
            return 0;
        }
        return -1;
    }
}
