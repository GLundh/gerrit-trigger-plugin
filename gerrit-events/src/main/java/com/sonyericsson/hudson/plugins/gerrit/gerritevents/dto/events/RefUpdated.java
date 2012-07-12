/*
 *  The MIT License
 *
 *  Copyright 2012 Sony Mobile Communications AB. All rights reserved.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */
package com.sonyericsson.hudson.plugins.gerrit.gerritevents.dto.events;

import com.sonyericsson.hudson.plugins.gerrit.gerritevents.dto.GerritEventType;
import com.sonyericsson.hudson.plugins.gerrit.gerritevents.dto.GerritJsonEvent;
import com.sonyericsson.hudson.plugins.gerrit.gerritevents.dto.attr.Account;
import com.sonyericsson.hudson.plugins.gerrit.gerritevents.dto.attr.RefUpdate;
import net.sf.json.JSONObject;

import static com.sonyericsson.hudson.plugins.gerrit.gerritevents.dto.GerritEventKeys.REFUPDATE;
import static com.sonyericsson.hudson.plugins.gerrit.gerritevents.dto.GerritEventKeys.SUBMITTER;

/**
 * A DTO representation of the ref-updated Gerrit Event.
 * @author jeblair
 */
public class RefUpdated extends GerritTriggeredEvent implements GerritJsonEvent {

    /**
     * The Gerrit ref update the event is related to.
     */
    protected RefUpdate refUpdate;

    /**
     * The ref update.
     *
     * @return the refupdate.
     */
    public RefUpdate getRefUpdate() {
        return refUpdate;
    }

    /**
     * The ref update.
     *
     * @param refUpdate the refupdate.
     */
    public void setRefUpdate(RefUpdate refUpdate) {
        this.refUpdate = refUpdate;
    }

    @Override
    public GerritEventType getEventType() {
        return GerritEventType.REF_UPDATED;
    }

    @Override
    public boolean isScorable() {
        return false;
    }

    @Override
    public void fromJson(JSONObject json) {
        if (json.containsKey(REFUPDATE)) {
            refUpdate = new RefUpdate(json.getJSONObject(REFUPDATE));
        }
        if (json.containsKey(SUBMITTER)) {
            this.account = new Account(json.getJSONObject(SUBMITTER));
        }
    }
}