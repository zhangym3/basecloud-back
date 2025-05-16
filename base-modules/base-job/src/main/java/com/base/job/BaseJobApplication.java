package com.base.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.base.common.security.annotation.EnableCustomConfig;
import com.base.common.security.annotation.EnableRyFeignClients;

/**
 * 定时任务
 * 
 * @author base
 */
@EnableCustomConfig
@EnableRyFeignClients   
@SpringBootApplication
public class BaseJobApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(BaseJobApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  定时任务模块启动成功   ლ(´ڡ`ლ)ﾞ  \n" +
                " .-------.       ____     __        \n" +
                " |  _ _   \\      \\   \\   /  /    \n" +
                " | ( ' )  |       \\  _. /  '       \n" +
                " |(_ o _) /        _( )_ .'         \n" +
                " | (_,_).' __  ___(_ o _)'          \n" +
                " |  |\\ \\  |  ||   |(_,_)'         \n" +
                " |  | \\ `'   /|   `-'  /           \n" +
                " |  |  \\    /  \\      /           \n" +
                " ''-'   `'-'    `-..-'              ");
    }
}
