package com.lti.handler;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.QueueTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

public class OrderSubmissionHandler extends FunctionInvoker<Integer,String> {
    private static final String queue_name="srivatsav-order-queue";
    private static final String connection="STORAGE_CONNECTION_STRING";

    @FunctionName("OrderSubmissionFunction")
    public String execute(@QueueTrigger(name = "orderId",queueName = "srivatsav-order-queue" ) int orderId, final ExecutionContext executionContext){
        return handleRequest(orderId,executionContext);
    }
}
