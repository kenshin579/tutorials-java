package com.keyhole.annotation;

import java.util.Date;

@DoItLikeThis(action = "PROCESS", shouldDoItLikeThis = true, description = "Class used for annotation example.")
public class AnnotatedTwo implements AnnotatedClass {

    @DoItLikeThat(shouldDoItLikeThat = true)
    private String field1;

    @DoItLikeThat(shouldDoItLikeThat = true, roles = {"web", "client"})
    private String field2;

    private String field3;
    private Date dateDoneLikeThis;

    /* setters and getters removed for brevity */

    @DoItWithAWhiffleBallBat(shouldDoItWithAWhiffleBallBat = true)
    public void doWhateverItIs() {
        // method implementation
    }

    public void verifyIt() {
        // method implementation
    }

}
