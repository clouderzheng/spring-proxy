### 代理模式

#### 基础原理  
代理模式的定义：为委托对象提供一种代理，以控制对委托对象的代理。在某些情况下，一个对象不适合或者不能直接引用另一个目标对象，而代理对象可以作为目标对象的委托，在客户端与目标对象之间起到中介的作用。
#### 代理模式角色  
代理模式包含3种角色：抽象角色、委托角色和代理角色
- 抽象角色  
通过接口或者抽象类的方式声明委托觉得所提供的业务方法
- 代理角色  
实现抽象类的接口，通过调用委托角色的业务逻辑方法来实现抽象方法，并且可以附加自己的操作。  
- 委托角色  
实现抽象角色，定义真实角色所要实现的业务逻辑，供代理角色调用。