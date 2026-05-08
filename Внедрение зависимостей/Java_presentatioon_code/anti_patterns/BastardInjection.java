package anti_patterns;

public class BastardInjection {

    interface PaymentProcess {
        void charge(double value);
    }

    static class PaymentMir implements PaymentProcess{
        @Override
        public void charge(double value){
            System.out.println("Charging " + value + " rubles via Mir");
        }
    }
    //В этом классе есть 2 конструктора - один для внедрения зависимостей, другой для работы
    static class PaymentService {
        private PaymentProcess process;

        //Для DI (и тестов)
        public PaymentService(PaymentProcess process){
            this.process = process;
        }
        //Для удобства
        public PaymentService(){
            this.process = new PaymentMir();
            System.out.println("Using PaymentMir implmentation");
        }

        public void processPayment(double value) {
            process.charge(value);
        }    
    }
    public static void demo(){
        System.out.println("\n----Control freak antipattern----");
        //при работе можно случайно вызвать пустой конструктор
        PaymentService service = new PaymentService();
        service.processPayment(100); 
        
    }
}
