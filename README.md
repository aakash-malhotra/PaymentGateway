# Payment Gateway System

A robust and scalable payment gateway implementation that facilitates payment processing between merchants and banks, supporting multiple payment methods and routing strategies.

## 🌟 Features

### Core Functionality
- **Multi-Client Support**: Seamlessly onboard and manage multiple merchants
- **Multiple Payment Methods**: Support for:
  - Credit/Debit Cards
  - UPI
  - Net Banking
- **Bank Integration**: Multiple bank support with configurable routing
- **Smart Routing**: 
  - Route transactions based on payment methods
  - Percentage-based traffic distribution between banks
  - Dynamic routing based on bank performance

### Client Management
- Client onboarding and removal
- Client-specific payment method configuration
- Customizable payment method support per client

### Payment Mode Features
- Add/Remove payment methods globally or per client
- Configurable validation rules per payment method
- List supported payment modes for specific clients

### Transaction Processing
- Secure payment processing
- Transaction validation
- Bank routing based on configurable rules
- Success/Failure handling

## 🏗 Architecture

### Component Overview
```ascii
+----------------+     +-------------------+     +------------------+
|     Client     | --> | Payment Gateway   | --> |      Banks      |
+----------------+     +-------------------+     +------------------+
                            |
                      +------------------+
                      |  Payment Modes   |
                      +------------------+
```

### Key Components
- **PaymentGateway**: Core payment processing engine
- **TransactionRouter**: Handles transaction routing logic
- **PayMode**: Payment method implementations
- **ClientService**: Client management and configuration
- **Memory**: In-memory data storage

## 🚀 Getting Started

### Prerequisites
- Java 11 or higher
- Maven 3.6 or higher
- Spring Boot 2.x

### Installation

1. Clone the repository
```bash
git clone https://github.com/yourusername/payment-gateway.git
```

2. Navigate to project directory
```bash
cd payment-gateway
```

3. Build the project
```bash
mvn clean install
```

4. Run the application
```bash
mvn spring-boot:run
```

## 💻 API Reference

### Client Management
```http
POST /pg/client/add
DELETE /pg/client/remove
GET /pg/client/exists
```

### Payment Modes
```http
GET /pg/paymode/all
PUT /pg/paymode/addPayModes
DELETE /pg/paymode/removePayModes
```

### Transactions
```http
POST /pg/transaction/process
GET /pg/transaction/distribution
```

## 🔧 Configuration

### Sample Client Configuration
```java
Client client = new Client("merchant1")
    .addPayMode(PayMode.CREDIT_CARD)
    .addPayMode(PayMode.UPI);
```

### Sample Routing Configuration
```java
TransactionRouter.setDistribution(Map.of(
    "HDFC", 70.0,
    "ICICI", 30.0
));
```

## 🧪 Testing

Run the test suite:
```bash
mvn test
```

### Test Coverage
- Unit Tests
- Integration Tests
- Payment Flow Tests
- Routing Logic Tests

## 📝 Design Patterns Used

- **Factory Pattern**: For payment mode creation
- **Strategy Pattern**: For payment processing
- **Dependency Injection**: Using Spring framework
- **Builder Pattern**: For complex object construction

## ⚡ Performance

- In-memory processing for fast response times
- Efficient routing algorithms
- Configurable transaction limits

## 🔒 Security Features

- Input validation
- Payment method-specific validation
- Secure transaction processing

## 🛠 Future Enhancements

- [ ] Dynamic routing based on bank success rates
- [ ] Advanced monitoring and metrics
- [ ] Transaction retry mechanisms
- [ ] Persistent storage support
- [ ] Advanced error handling
- [ ] Rate limiting
- [ ] Circuit breaker implementation

## 👥 Authors

- Your Name - *Initial work* - [aakash-malhotra](https://github.com/aakash-malhotra)

## 🙏 Acknowledgments

- Spring Framework team
- Java community
- All contributors

## 📊 Project Status

Project is: _in development_

## 📞 Contact

Created by [@aakash-malhotra](https://github.com/aakash-malhotra) - feel free to contact me!
