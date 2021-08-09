### jdk-proxy

#### 动态代理步骤
- 创建代理对象的接口（jdk动态代理必须针对接口）
- 创建代理对象的接口实现（具体逻辑，传入InvocationHandler中反射调用的具体对象）
- 创建InvocationHandler，增强逻辑，反射调用代理对象
- 创建代理对象并真正调用proxy.create()

#### 实现原理
- 最终实现就是通过InvocationHandler接口的实现类，调用method.invoke(proxy, args)实现，这里本质还是反射的调用