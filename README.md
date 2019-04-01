本demo是意图：
1、使用spring cloud的feign-client可以很方便地在微服务间发送http请求，但是有一个不够好的地方就是不支持get请求使用自定义的bean作为参数接收对象，即将参数的属性作为url参数拼接在url后面。
2、虽然可以使用post请求，但在一些需要使用restful的时候，却违背了restful的初衷，尽管严格执行restful风格的url是有点太过于执着。
3、因此这个项目是为了使用feign-client的项目可以完美使得dto接收get请求参数而又不用一个个requestparam注解去写，这样到加减参数的时候会影响到接口的变动，影响比较大。


如何达到目的：
1、使用aop切面，拦截发送请求：
@Around(value = "execution(* feign.Client.execute(..)) ")
将get请求捕获，解析get请求里面的requestbody解析成json，然后重新构建request请求，去掉header里面content-type有json/application的头参数，设置空的requestbody参数，将参数转化解析为键值对拼接在url后面，然后再发送请求。


注：
可能以上方案比较鸡肋和简单粗暴，但这是我目前能想到的方案。之前试过继承feign-client启动的配置，然后重写配置方法，虽然后面能重写的get请求使用dto后生成的请求对象，使之能像普通的get请求那样解析request为queryparam的格式，但在调用方使用feign-client时，最终发送请求还是会生成带requestbody和content-type有json/application的header参数，导致最终feign-client认为本次请求是post请求，而造成405异常。
