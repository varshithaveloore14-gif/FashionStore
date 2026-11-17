# 🛍️ FashionStore – Full-Stack E-Commerce Web Application

FashionStore is a Java-based full-stack e-commerce web application that allows users to explore fashion products, select product sizes, manage shopping carts, and place orders.

The application is developed using Java EE technologies, JSP, Servlets, JDBC, and MySQL, following a structured MVC-based architecture.

## 🚀 Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend development |
| JSP | Dynamic web pages |
| Servlets | Request handling |
| JDBC | Database connectivity |
| MySQL | Database management |
| HTML | Web page structure |
| CSS | Styling |
| JavaScript | Client-side functionality |
| Apache Tomcat 10.1 | Application server |
| Maven | Project management |
| Eclipse IDE | Development environment |

## ✨ Features

- **User Registration:** New users can create an account.
- **User Login:** Secure user authentication.
- **Product Browsing:** Explore available fashion products.
- **Category-Based Products:** Browse products based on categories.
- **Product Size Selection:** Select available sizes for products.
- **Shopping Cart:** Add and remove products from the cart.
- **Cart Management:** Manage selected products and quantities.
- **Order Placement:** Place orders using cart items.
- **Database Integration:** Store and retrieve application data using MySQL.

## 🏗️ Project Architecture

The application follows a layered MVC-style architecture:

1. **View Layer:** JSP, HTML, CSS, and JavaScript.
2. **Controller Layer:** Java Servlets handle incoming requests.
3. **Model Layer:** Java classes manage application data and business logic.
4. **Database Layer:** JDBC connects the application to MySQL.

## 🗄️ Database Design

The application uses MySQL with the following tables:

| Table Name | Description |
|---|---|
| `users` | Stores user information |
| `categories` | Stores product categories |
| `product` | Stores product details |
| `product_sizes` | Stores available product sizes |
| `cart` | Stores user cart information |
| `cart_items` | Stores individual cart products |
| `orders` | Stores order details |
| `order_items` | Stores products associated with orders |

## ⚙️ Installation and Setup

### Prerequisites

- Java JDK
- Eclipse IDE
- Apache Tomcat 10.1
- MySQL Server
- Maven

### Step 1: Clone the Repository

```bash
git clone https://github.com/varshithaveloore14-gif/FashionStore.git
```

### Step 2: Import into Eclipse

1. Open Eclipse IDE.
2. Select **File → Import**.
3. Choose **Existing Maven Projects**.
4. Select the cloned FashionStore project.
5. Click Finish.

### Step 3: Configure MySQL

1. Start your MySQL server.
2. Create the required database.
3. Create the tables listed in the Database Design section.
4. Update the database connection details in your `DBConnection` class.

### Step 4: Configure Apache Tomcat

1. Add Apache Tomcat 10.1 to Eclipse.
2. Configure the project with the Tomcat server.
3. Build the Maven project.
4. Run the application on the server.

### Step 5: Access the Application

Open your browser and navigate to:

```text
http://localhost:8080/FashionStore/
```

*Note: The context path may vary depending on your Eclipse/Tomcat configuration.*

## 📂 Project Structure

```text
FashionStore/
│
├── src/
│   └── main/
│       ├── java/
│       │   ├── controller/
│       │   ├── dao/
│       │   ├── model/
│       │   └── util/
│       │
│       └── webapp/
│           ├── css/
│           ├── js/
│           ├── images/
│           ├── WEB-INF/
│           └── *.jsp
│
├── pom.xml
└── README.md
```

*The folder structure above represents a typical Maven organization; adjust it to match your actual repository.*

## 🔮 Future Enhancements

- Admin dashboard for product management.
- Online payment gateway integration.
- Order tracking functionality.
- Product search and filtering.
- User profile management.
- Wishlist functionality.

## 👩‍💻 Developer

**Varshitha**

B.Tech – Computer Science and Engineering (AI & ML)

GitHub: [varshithaveloore14-gif](https://github.com/varshithaveloore14-gif)

---

⭐ If you find this project interesting, feel free to explore the repository!
