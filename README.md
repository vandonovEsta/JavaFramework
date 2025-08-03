# JavaFramework

A Java-based test automation framework for testing the Trello API.

## Features

The framework currently includes implementations for:

- Board management
- List management
- Card management
- Checklist management

## Getting Started

### 🧩 Download Dependencies

Run the following command to clean and install dependencies:

```bash
mvn clean install
```

To execute the test suite:

```bash
mvn test
```

Alternatively, you can run the tests directly through your IDE (e.g., IntelliJ IDEA or Eclipse).
Environment Setup

Before running the tests, ensure you have the following environment variables set:

- TRELLO_API_KEY

- TRELLO_TOKEN

These are required for authenticating with the Trello API.

#### TODO:
Changed approach
- Started creating each method in the service classes accepting a request DTO
	that I abandoned at one point as most of the query parameters were optional.
	Should add both overloads so there is an option for more precise requiests.
- Need another abstraction for extracting data from pojo files, the final assertion is a bit unreadable at the moment
- Finish Dto Fakers
- Need to double-check single responsibility in all classes