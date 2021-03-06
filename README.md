# Welcome to CS 2212 - Winter 2016

To get started, you will need to run these commands in your terminal to run the program.

```

mvn clean
mvn package
java -jar target/team01-TRAINR-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

## Configure Git for the first time

```
git config --global user.name "Your Name"
git config --global user.email "youremail@uwo.ca"
```


## Generate an SSH key and add it to your account

On Linux, UNIX, Cygwin, or Git Bash, generate a key:

```
ssh-keygen -t rsa -b 4096
```

This will generate a private key (`~/.ssh/id_rsa`) as well as a public key (`~/.ssh/id_rsa.pub`).
**Do not share your private key with others.**

Display your public key:

```
cat ~/.ssh/id_rsa.pub
```

Copy the entire contents of the key, and associate it with your Bitbucket account:

* Click the avatar icon in the top right corner
* Select *Manage account* from the drop down list.
* Select *SSH keys* from the sidebar.
* Click *Add key*.
* Paste the contents of your public key in the *Key* field.  Be sure to paste the entire file.
* Click *Add key*.

## Working with your repository

Clone your repository onto your local system:

```
git clone ssh://git@repo.gaul.csd.uwo.ca:7999/cs2212_w2016/team01.git
```

*Remember*: do *not* develop on the `master` or `dev` branches.  Instead, in
JIRA, create a feature branch off of `dev` to do your work.  Do your
development on your feature branch.  Then, when you are finished with your
feature, push your changes, and open a pull request to merge your changes back
into the `dev` branch.

To submit your assignment, open a pull request to merge your changes on `dev`
back into the `master` branch.
=======
# Welcome to CS 2212 - Winter 2016

To setup the program, you will need to configure these paths to the corresponding credentials & token files.
The path variables are located in the MainWindow.java file as class variables.

```
	static final String CREDENTIALS_FILE_PATH = "/PATH/Team1Credentials.txt";
	static final String TOKENS_FILE_PATH = "/PATH/Team1Tokens.txt";
```

To start the program, you will need to run these commands in your terminal to run the program.

```
mvn clean
mvn package
java -jar target/team01-TRAINR-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

## Configure Git for the first time

```
git config --global user.name "Your Name"
git config --global user.email "youremail@uwo.ca"
```


## Generate an SSH key and add it to your account

On Linux, UNIX, Cygwin, or Git Bash, generate a key:

```
ssh-keygen -t rsa -b 4096
```

This will generate a private key (`~/.ssh/id_rsa`) as well as a public key (`~/.ssh/id_rsa.pub`).
**Do not share your private key with others.**

Display your public key:

```
cat ~/.ssh/id_rsa.pub
```

Copy the entire contents of the key, and associate it with your Bitbucket account:

* Click the avatar icon in the top right corner
* Select *Manage account* from the drop down list.
* Select *SSH keys* from the sidebar.
* Click *Add key*.
* Paste the contents of your public key in the *Key* field.  Be sure to paste the entire file.
* Click *Add key*.

## Working with your repository

Clone your repository onto your local system:

```
git clone ssh://git@repo.gaul.csd.uwo.ca:7999/cs2212_w2016/team01.git
```

*Remember*: do *not* develop on the `master` or `dev` branches.  Instead, in
JIRA, create a feature branch off of `dev` to do your work.  Do your
development on your feature branch.  Then, when you are finished with your
feature, push your changes, and open a pull request to merge your changes back
into the `dev` branch.

To submit your assignment, open a pull request to merge your changes on `dev`
back into the `master` branch.
=======
# Welcome to CS 2212 - Winter 2016

Before running, make sure you are using a 32-bit operating system, with a display of 1480 by 800 or greater for optimum results. 
Image analysis of the nutrition label image will only run on a 32-bit system. 
To start the program, you will need to run these commands in your terminal to run the program.

UI for Uploading / Viewing Transformation Photos is not included in this version.

```
mvn clean
mvn package
java -jar target/team01-TRAINR-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

## Configure Git for the first time

```
git config --global user.name "Your Name"
git config --global user.email "youremail@uwo.ca"
```


## Generate an SSH key and add it to your account

On Linux, UNIX, Cygwin, or Git Bash, generate a key:

```
ssh-keygen -t rsa -b 4096
```

This will generate a private key (`~/.ssh/id_rsa`) as well as a public key (`~/.ssh/id_rsa.pub`).
**Do not share your private key with others.**

Display your public key:

```
cat ~/.ssh/id_rsa.pub
```

Copy the entire contents of the key, and associate it with your Bitbucket account:

* Click the avatar icon in the top right corner
* Select *Manage account* from the drop down list.
* Select *SSH keys* from the sidebar.
* Click *Add key*.
* Paste the contents of your public key in the *Key* field.  Be sure to paste the entire file.
* Click *Add key*.

## Working with your repository

Clone your repository onto your local system:

```
git clone ssh://git@repo.gaul.csd.uwo.ca:7999/cs2212_w2016/team01.git
```

*Remember*: do *not* develop on the `master` or `dev` branches.  Instead, in
JIRA, create a feature branch off of `dev` to do your work.  Do your
development on your feature branch.  Then, when you are finished with your
feature, push your changes, and open a pull request to merge your changes back
into the `dev` branch.

To submit your assignment, open a pull request to merge your changes on `dev`
back into the `master` branch.
>>>>>>> branch 'dev' of ssh://git@repo.gaul.csd.uwo.ca:7999/cs2212_w2016/team01.git
