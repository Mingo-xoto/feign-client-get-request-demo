本项目是意图：
1、使用spring cloud的feign-client可以很方便地在微服务间发送http请求，但是有一个不够好的地方就是不支持get请求使用自定义的bean作为参数接收对象，即将参数的属性作为url参数拼接在url后面。
2、虽然可以使用post请求，但在一些需要使用restful的时候，却违背了restful的初衷，尽管严格执行restful风格的url是有点太过于执着。
3、因此这个项目是为了使用feign-client的项目可以完美使得dto接收get请求参数而又不用一个个requestparam注解去写，这样到加减参数的时候会影响到接口的变动，影响比较大。