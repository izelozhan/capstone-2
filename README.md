# DELI-cious 🥪

**DELI-cious** is a console-based Java application that simulates a customizable sandwich shop experience. Users can create their orders by choosing sandwich types, drink flavors, chip options, and other add-ons. The application guides users through a multi-step ordering process and generates a receipt at checkout.

---
## ✨ Features

-  Custom sandwich builder: choose size, bread, meat, cheese, toppings, sauces
-  Support for signature sandwich presets
-  Add chips and drinks with customizable options
-  Receipt generation and file output

---

## 📸 Application Screens

**Home Screen and Order Screen** <br>
The user starts the application from this screen by clicking "New Order." First, they enter their name, and then they are directed to the order screen. From there, they can choose to add a sandwich, add a drink, or add chips. They can also choose to check out or cancel the order.

![delicious1](https://github.com/user-attachments/assets/aad78387-45bc-4aab-a0fa-9069113f77ae)

**Add Sandwich** <br>
On the "Add Sandwich" screen, the user can either create a new sandwich from scratch or select one of the existing signature sandwiches and customize it by adding or removing ingredients.

![delicious2](https://github.com/user-attachments/assets/e2651f53-b3ea-4680-9fec-8fc43a41f671)

![delicious3](https://github.com/user-attachments/assets/164a5fdf-c253-4642-bc5a-5729c06e9286)

![delicious4](https://github.com/user-attachments/assets/14e41326-506e-4183-b7d8-3a7760c40ded)

**Add Drink** <br>
On the "Add Drink" and "Add Chips" pages, the user can select the size and type of the item and add it to their order.

![delicious5](https://github.com/user-attachments/assets/09ff586f-defc-40ca-8eea-52d1f78c93ef)

**Checkout** <br>
On the checkout screen, the user can view the order summary and total price, including all sandwiches and their details, and confirm the order.

![delicious6](https://github.com/user-attachments/assets/0516781a-721c-44f2-a15b-05ed4afa4a6c)

**Cancel Order** <br>
The Cancel Order option discards the current order and returns the user to the home page.

![delicious7](https://github.com/user-attachments/assets/ec7e0158-ed91-4208-9aa5-97225468eefa)

---

## 🏃 How to Run

- To get started clone the repository to your local:

  `git clone https://github.com/izelozhan/capstone-2.git`

- After cloning the repository, navigate into the project directory and run it.

---

## 🧠 Interesting Code Snippet

Product class:
All materials such as toppings, drinks, chips are based on Product class, which holds all pricing options, product name.
The methods it has allow me to get price for the selected sandwich size.

Selection:
For chips, and drinks; user can choose a custom size, and that's not dependent on the bread size. As bread size is dependent on it's own size, bread also stored as a selection.
Selection is a generic class, which stores the product, and user selected SizePrice class: which holds selected Size, and a double Price. 

![interestingcode](https://github.com/user-attachments/assets/936f2a9d-3acc-43da-ae79-0d7857256e57)






