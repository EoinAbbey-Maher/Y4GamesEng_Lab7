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

		// Creation Of Burger
		public void produce(){
			synchronized(this)
			{
				System.out.println("Producer Produced Burger no. " + num); 
		
				//notifies second thread of Completion
				notify();

			}
			
		}

		//Consumption Of Burger
		public void consume()
		{
			synchronized(this)
			{
				System.out.println("Consumer Consumed Burger no. " + num);

				//notifies first thread of Completion
				notify();

				num++;
			}
			
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
				synchronized(this)
				{
					while(p != c){
					}
					Burger burg = new Burger();
					try{
						burg.produce();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					burgerBuf = burg;
					p = p + 1;
					try{
					Thread.sleep(1000);
					}
					catch(Exception e)
					{
					
					}
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
				synchronized(this)
				{
					while(p <= c){
					}
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
