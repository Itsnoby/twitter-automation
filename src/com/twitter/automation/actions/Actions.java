package com.twitter.automation.actions;

import com.twitter.automation.utils.ObjectsCollection;
import com.twitter.automation.utils.base.BaseActions;

public final class Actions {
    private static ObjectsCollection<BaseActions> actions = new ObjectsCollection<BaseActions>();

     public static void clear() {
         actions.clear();
    }

    public static GeneralActions generalActions() {
        return actions.getInstance(GeneralActions.class);
    }

    public static TweetsActions tweetsActions() {
        return actions.getInstance(TweetsActions.class);
    }

    public static UsersActions usersActions() {
        return actions.getInstance(UsersActions.class);
    }
}
