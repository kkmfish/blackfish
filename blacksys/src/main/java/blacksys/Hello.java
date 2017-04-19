package blacksys;


public class Hello {
	String name;
	Printer printer;
	
	public String sayHello() {
		/** 프로퍼티로 DI 받은 이름을 이용해 문구 만들기 */
		return "Hello " + name;
	}
	
	public void print() {
		/**
		 * DI에 의해 의존 오브젝트로 제공받은 Printer 타입의 오브텍트에게
		 * 출력 작업을 위임.
		 * 구체적으로 어떤 방식으로 출력하는지는 상관하지 않는다.
		 * 또한 어떤 방식으로 출력하도록 변경해도 Hello의 코드는 수정할 필요가 없다.
		 */
		this.printer.print(sayHello());
	}
	
	public void setName(String name) {
		/**
		 * 문구에 쓸 이름을 스트링 값으로 DI 받음
		 */
		this.name = name;
	}
	
	public void setPrinter(Printer printer) {
		/**
		 * 출력을 위해 사용할 Printer 인터피에스를 구현한 오브젝트를 DI
		 */
		this.printer = printer;
	}

}
