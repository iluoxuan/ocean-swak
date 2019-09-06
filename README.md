#  参考闲鱼swak -ppt实现

* 为multiple-core提供平台和业务分离能力

* 不仅为多渠道实现，也可以为处理if—else沉淀平台能力

##  列子
* 引入pom

```xml

        <dependency>
            <groupId>com.ocean.swak</groupId>
            <artifactId>ocean-swak</artifactId>
        </dependency>

```

* 分析业务上不变的和可变的

* 把可变的业务提取 成接口

```java

@SwakInterface(desc = "发财的各种方式")
public interface PayBiz {

    String getPayUrl(String channel, SwakContext context);
}

```

* 写不同的实现类

```java

// tags 标识 manwei 漫威
@SwakBiz(tags = "manwei")
public class ManWeiPayBiz implements PayBiz {

    @Override
    public String getPayUrl(String channel, SwakContext context) {

        return "manwei---- getpayUrl";
    }
}

// tag 标识 dc 
@SwakBiz(tags = "dc")
public class DcPayBiz implements PayBiz {

    @Autowired
    private BigBigService bigBigService;

    @Autowired
    private SwakService swakService;

    @Override
    public String getPayUrl(String channel, SwakContext context) {


        bigBigService.bigTest();

        swakService.test();

        return "dc --- get pay Url";
    }
}

@Primary
@SwakBiz
public class DefaultPayBiz implements PayBiz {

    @Override
    public String getPayUrl(String channel, SwakContext context) {
        return "default --- biz";
    }
}


```
* 然后 注入接口 调用

```java


@Service
public class HejService {

    // 注入 接口
    @Autowired
    private PayBiz payBiz;

    @Autowired
    private SwakService swakService;

    public void getBigPlan(String param) {

        SwakContext context = new SwakContext();
        context.setTags(Lists.newArrayList("dc"));
        
        // 调用接口 【采用 SwakContext 方式 】
        String result = payBiz.getPayUrl("dc", context);
        System.out.println("===");
        System.out.println(result);


        swakService.test();


    }
}


```


## 标签冲突解决

* tag1 tag2 tag3 

* 有一个接口开始有 tag1 ，tag2 tag3的两个实现 ，但是有一个业务需要在tag1，tag2中都有

### 执行方式一共有
* 命中一个tag就执行，返回结果
* 命中 输入[tag1, tag2] ，tag1-->有，否 tag2 【优先级配置由调用方配置】
* 思考: 如何配置接口的调用方式【结合配置平台】

## 支持rpc调用
### 场景
* A 业务是主干业务抽离的，然后需要 远程服务的方式给主干应用提供服务
* 接口即是主干业务的提供的扩展
* 主干应用开发人员来说，应该提供单个接口以支持所有业务类型
* 怎么定义Do
```java

 public class ItemDo{
    
    private String type;
    private Integer status;
    private Map<String, Object> bizMap;
    
 }

```








