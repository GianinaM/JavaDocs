package com.teamnet.examples;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Created by Gianina.Carp on 7/10/2017.
 */
public class MyUnitTest{
    @Test
    public void testConcatenate() {
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate("one", "two");

        assertEquals("onetwo", result);

    }

    @Test
    public void testConcatenateNulls() {
        MyUnit myUnit = new MyUnit();

        String result = myUnit.concatenate(null, null);

        assertEquals("nullnull", result);

    }

    @Test
    public void testGetTheBoolean() {
        MyUnit myUnit = new MyUnit();

        assertTrue (myUnit.getTheBoolean());

////        assertFalse(myUnit.getTheBoolean());
    }

    @Test
    public void testGetTheObject() {
        MyUnit myUnit = new MyUnit();

        assertNull(myUnit.getTheObject());

//        assertNotNull(myUnit.getTheObject());
    }

    @Test
    public void testGetTheSameObject() {
        MyUnit myUnit = new MyUnit();

//        assertSame   (myUnit.getTheSameObject(),
//                myUnit.getTheSameObject());

        assertNotSame(myUnit.getTheSameObject(),
                myUnit.getTheSameObject());
    }

    @Test
    public void testWithMatchers() {
        assertThat("this string", is("this string"));
        assertThat(123, is(123));
        assertThat(123, not( is(345) ) );
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testForExceptions1() {
//        MyUnit myUnit = new MyUnit();
//
//        myUnit.throwIllegalArgumentException();
//    }
//
//    @Test
//    public void testForExceptions2() {
//        MyUnit myUnit = new MyUnit();
//
//        try {
//            myUnit.throwIllegalArgumentException();
//
//            fail("expected IllegalArgumentException");
//
//        } catch (IllegalArgumentException e) {
//            //ignore, this exception is expected.
//        }
//    }


//
}
