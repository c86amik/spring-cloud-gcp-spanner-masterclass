# spring-cloud-gcp-spanner-masterclass

## A good understanding of the usage of Google Cloud Spanner with Spring Boot

### Software Required
* [Java 1.8](https://www.oracle.com/in/java/technologies/javase/javase8-archive-downloads.html)
* [Spring tool Suite](https://spring.io/tools) or [Eclipse](https://www.eclipse.org/downloads/packages/)
* [Apache Maven](https://maven.apache.org/download.cgi)
* [Git Bash](https://gramfile.com/git-bash-download/)
* [Google Cloud SDK](https://cloud.google.com/sdk/docs/install) - Google Cloud SDK to register Google project locally
* [Postman](https://www.postman.com/downloads/)

### Steps to execute Google Cloud SDK
* Install Google Cloud SDK
* After successful installation open the Cloud SDK
* Run the command as `gcloud auth application-default login`
* It will open your default browser and authorize the google account that you have used in Google Cloud Console to configure the services
* After that it will store the <strong>application_default_credentials.json</strong> file in your local drive.

### Steps to configure Google Cloud Spanner
* Open [Google Cloud Console] (https://console.cloud.google.com/)
* Create a Project
* Go to Spanner from the left navigation panel
* Create an Instance. I have created one instance named as `springcavaj-user`
* After that create a Database. I have created a database named as `springcavajspanner`
* After that create a Table. I have created a table named as `user`
* A DDL script is also provided in the `src/main/resources` folder
* You can run the script in the SQL area

### Steps to clone and run the application
* Follow the steps to install and configure [Google Cloud SDK](https://github.com/c86amik/spring-cloud-gcp-sql-storage-masterclass#steps-to-execute-google-cloud-sdk) with your Google account in your local machine. So that your local machine can connect with Google Cloud Components.
* Open Git Bash or even you can open Command Prompt (if you are using Windows) or Terminal (if you are using MAC) in your machine
* Clone the application from github.com as   
<code>git clone https://github.com/c86amik/spring-cloud-gcp-spanner-masterclass.git</code>
* Open either <strong>STS</strong> or <strong>Eclipse</strong> and import the application as <strong>Maven</strong> project
* After the application got successfully imported in either <strong>STS</strong> or <strong>Eclipse</strong>
* Execute the SQL scripts as mentioned in the above [Steps to configure Google Cloud Spanner](https://github.com/c86amik/spring-cloud-gcp-sql-storage-masterclass#steps-to-configure-google-cloud-spanner) execution steps
* Right Click on the application, select the <strong>Run As</strong> option, and then select <strong>Spring Boot App</strong>
* The application will start in the port <strong>7122</strong>
* Open the Postman and test the REST Endpoints

### Testing using Postman
<ol>
<li><strong>Get all Users as stored in Google Cloud Spanner</strong> - localhost:7122/allUsers</li>
<li><strong>Save a User in Google Cloud Spanner</strong> - localhost:7122/saveUser</li>
<li><strong>Get a User by ID from Google Cloud Spanner</strong> - localhost:7122/getUser/{id}. Here <strong>{id}</strong> is the id of the record stored in Cloud Spanner</li>
<li><strong>Update an existing User by ID in Google Cloud Spanner</strong> - localhost:7122/updateUser/{id}. Here <strong>{id}</strong> is the id of the record stored in Cloud Spanner</li>
<li><strong>Delete a User from Google Cloud Spanner</strong> - localhost:7122/deleteUser/{id}. Here <strong>{id}</strong> is the id of the record stored in Cloud Spanner</li>
</ol>

#### Dummy JSON object
* Body for the <strong>POST</strong> method to save a User in Google Cloud Spanner. For this method the body type is `raw` -> `json`.

<code>{
    	"userId" : 1,
		"name" : "Test Name",
		"mobileNo" : "1234567890"
}</code>

* Body for the <strong>PUT</strong> method to upload an existing User record in Google Cloud Spanner

<code>{
    	"userId" : 1,
		"name" : "Test Name",
		"mobileNo" : "1234567891"
}</code>