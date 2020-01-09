public class BurgerJoint {
	static int buf;
	static Burger burgerBuf;
	static int p = 0;
	static int c = 0;
	static int n = 500;
	
	// ********************************************************
	// 					Burger Class
	// ********************************************************
	public static class Burger{
		
		public static int num = 0;

		public void produce(){
			System.out.println("Producer Produced Burger no. " + num); 
		
			notify();

			//Thread.sleep(1000);
			
		}

		public void consume()
		{
			System.out.println("Consumer Consumed Burger no. " + num);

			notify();

			num++;
			//Thread.sleep(1000);

			
		}

	}


	// ********************************************************
	// 					Producer Class
	// ********************************************************
	public static class Producer extends Thread{
		int a = 0;

		public void run(){
			System.out.println("Producer Starting");
			while(p < n){
				while(p != c){
					//System.out.println("Short order cook spinning");
				}
				Burger burg = new Burger();
				//System.out.println("Making a Burger: " + p);
				try{
					burg.produce();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				burgerBuf = burg;
				//buf = a;
				p = p + 1;
				
				try{
				Thread.sleep(1000);
				}
				catch(Exception e)
				{
					
				}
			}
			System.out.println("Finished Making Burgers");
		}
	}

	// ********************************************************
	// 					Consumer Class
	// ********************************************************
	public static class Consumer extends Thread{
		int b = 0;
		Burger bufferBurg;
		public void run(){
			System.out.println("Consumer Starting");
			while(c < n){
				while(p <= c){
					//System.out.println("Starving waiting on a burger!");
				}
				//System.out.println("Eating Burger: " + c);
				bufferBurg = burgerBuf;

				bufferBurg.consume();
				c = c + 1;
				try{
				Thread.sleep(1000);
				}
				catch(Exception e)
				{

				}
			}

			System.out.println("Finished Making Burgers");
		}
	}
	
	// ********************************************************
	// 					Main Function
	// ********************************************************
	public static void main(String[] args) {		
		Burger burger = new Burger();
		
		Producer producer = new Producer();
		Consumer consumer = new Consumer();
		
		producer.start();
		consumer.start();

		try{
			producer.join();
			}
			catch(Exception e)
			{
			}
		
		try{
			consumer.join();
			}
		catch(Exception e)
			{
			}
		
		
	}
}
