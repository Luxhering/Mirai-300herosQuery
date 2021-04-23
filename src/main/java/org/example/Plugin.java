package org.example;

import function.TYMatchQuery;
import function.qinYe;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import tool.initData;

import java.io.IOException;

public final class Plugin extends JavaPlugin {
    public static final Plugin INSTANCE = new Plugin();

    private Plugin() {
        super(new JvmPluginDescriptionBuilder("org.example.plugin", "1.2")
                .name("300icu专用")
                .author("luxhering")
                .build());
    }
    @Override
    public void onEnable() {
        System.out.println(getConfigFolder());
        getLogger().info("300icu专用插件");
        initData.init();
        qinYe qinYe = new qinYe();
        TYMatchQuery tyMatchQuery = new TYMatchQuery();
        GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {
            try {
                qinYe.Fuck(event);
                tyMatchQuery.tyMatch(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}