package com.keyhole.annotation;

import java.util.Date;

@DoItLikeThis
public class AnnotatedOne implements AnnotatedClass {

    @DoItLikeThat(shouldDoItLikeThat = false)
    private String field1;

    @DoItLikeThat(shouldDoItLikeThat = true, roles = {"admin", "root"})
    private String field2;

    private String field3;
    private Date dateDoneLikeThis;

    /* setters and getters removed for brevity */

    @DoItWithAWhiffleBallBat(batType = WhiffleBallBat.BLACK_PLASTIC, shouldDoItWithAWhiffleBallBat = true)
    public void doWhateverItIs() {
        // method implementation
    }

    public void verifyIt() {
        // method implementation
    }

}