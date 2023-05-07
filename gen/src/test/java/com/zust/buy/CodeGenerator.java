package com.zust.buy;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.zust.buy.common.entity.BaseEntity;

import java.util.ArrayList;

public class CodeGenerator {
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 配置策略
        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");    // D:\IDEA_Work_Space\BUY
        gc.setOutputDir(projectPath + "/common/src/main/java");
        gc.setAuthor("xuxiaoqian");
        gc.setOpen(false);
        gc.setFileOverride(true);// 是否覆盖原来生成的
//        gc.setServiceName("%sService");// 去除service的i前缀
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
//        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 2、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/buy?useSSL=false&useUnicode=true&characterEncoding=UTF-8");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("12345678");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 3、包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.zust.buy");
        pc.setModuleName("new");
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        // 4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("t_order");// 设置要映射的表名
        strategy.setTablePrefix("t_");
        strategy.setNaming(NamingStrategy.underline_to_camel);// 下划线转驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // entity 继承的父类
        strategy.setSuperEntityClass(BaseEntity.class.getName());
        strategy.setSuperEntityColumns("deleted", "create_time", "update_time");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);// 生成 @RestController 控制器
        strategy.setLogicDeleteFieldName("deleted");
        // 自动填充配置
        TableFill createTime = new TableFill("create_time", FieldFill.DEFAULT);
        TableFill updateTime = new TableFill("update_time", FieldFill.DEFAULT);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);
        mpg.setStrategy(strategy);

        // 5、执行
        mpg.execute();

    }
}
