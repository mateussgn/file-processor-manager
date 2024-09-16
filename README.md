# File Processor Manager
**The intent of this project is develop a use case for a cloud event driver architecture.**

## Description
You are developing a service in Spring Boot that needs to process files uploaded by users, such as financial reports. The system must receive the upload request, store the file information, process the content, and notify the user about the completion of the processing.

1.  **HTTP POST request:**
- Make a POST request to https://ec2-44-222-68-132.compute-1.amazonaws.com:8080/api/files/upload
- Using the following body:
```json
{
  "filename": "filename",
  "fileSize": 12345,
  "fileType": "csv",
  "userId": "userid1234",
  "email": "user@user.com"
}
```

2.  **Publish the message to an SQS queue:**
- After receiving the upload request, the service should publish the file metadata and user information to an AWS SQS queue so that file metadata processing can be done later.
- There is an SQS service to handle this queue publishing.

3.  **Consume the SQS queue:**
- A consumer class is responsible for consume the AWS SQS Queue messages.

4.  **Process the SQS Queue Message**
- A File processor will process the message. The file processor implementation will count the number off lines in the queue message.
- There is a File Processor interface and a CountLinesFileProcessor which implements it interface and count the lines.
- The interface allows other kind of implementations, for example count the number of characters in a queue message.

5. **Save to DynamoDB:**
- Once the message queue is processed, then the processing result is appended to the queue message data creating a new model which will be saved in an AWS DynamoDB Table.
- With a DynamoDb Service id possible to save FileMetadataModal data. Containing:
`filename`, `fileNumberOfLines`, `fileSize`, `fileType`, `userId` and `email`.

6. **Publish a message in SNS:**
- After store the data in the database, it's time to send a notification for an AWS SNS Topic, which could be subscribed in order to receive notifications about the file metadata processing.
- The notification will have the same data stored in the DynamoDB.
- Fallows this format:
`{"filename": "filename", "fileNumberOfLines": 1, "fileSize": 12345, "fileType": "csv", "userId": "userid1234", "email": "user@user.com"}`

## Documentation

- [OpenApi Documentation](file.processor.manager/src/main/resources/openapi.yaml)