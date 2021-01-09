package org.example;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.File;

import static com.amazonaws.services.s3.model.CryptoStorageMode.ObjectMetadata;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        System.out.println( "Hello World!" );
        AWSCredentials credentials = new BasicAWSCredentials(
                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX",
                "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
        );
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1)
                .build();
        String bucketName = "naresh-bucket-demo";

        if(s3client.doesBucketExist(bucketName)) {
            /*LOG.info("Bucket name is not available."
                    + " Try again with a different Bucket name.");
            return;*/
        }

        //s3client.createBucket(bucketName);
        System.out.println("bukcet created");
        String stringObjKeyName = "PIC.JPG";
        String fileObjKeyName = "PIC.JPG";
        String fileName = "C:\\Users\\hp\\Downloads\\PIC.JPG";
// Upload a file as a new object with ContentType and title specified.

        PutObjectRequest request = new PutObjectRequest(bucketName, fileObjKeyName, new File(fileName));
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType("plain/text");
        metadata.addUserMetadata("title", "someTitle");
        request.setMetadata(metadata);
        s3client.putObject(request);
    }
}
