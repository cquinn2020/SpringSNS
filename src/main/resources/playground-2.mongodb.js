const database = "SNS_USERS";
const collection = "users";

// Switch to the database.
use(database);

// Create a new user document.
const newUser = {
  id: 1,
  name: "John Doe",
  email: "casey.quinn@tamu.edu",
  password: "password",
};

// Insert the new user document into the collection.
db.getCollection(collection).insertOne(newUser);
