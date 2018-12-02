package me.zhang.laboratory.ui;

import me.zhang.laboratory.ui.base.MenuActivity;

public class MainActivity extends MenuActivity {

    @Override
    protected void prepareMenu() {
        addMenuItem("Corner Layout", CornerActivity.class);
        addMenuItem("Scroller", ScrollerActivity.class);
        addMenuItem("Coordinator", CoordinatorActivity.class);
        addMenuItem("MultiLauncher", MultiLauncherActivity.class);
    }

}