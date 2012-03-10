/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.integration.dsl

import org.springframework.integration.store.{SimpleMessageStore, MessageStore}
import java.util.UUID
import org.springframework.util.StringUtils


/**
 * @author Oleg Zhurakousky
 */
object aggregate {
  /**
   * 
   */
  def apply() = new SendingEndpointComposition(null, new MessageAggregator()) {
    def where(name:String = "$aggr_" + UUID.randomUUID().toString.substring(0, 8),
              keepReleasedMessages:Boolean = false,
              messageStore:MessageStore = new SimpleMessageStore,
              sendPartialResultsOnExpiry:Boolean = true,
              expireGroupsUponCompletion:Boolean = false) =
      new SendingEndpointComposition(null, new MessageAggregator(name = name,
                                                        keepReleasedMessages = keepReleasedMessages,
                                                        messageStore = messageStore,
                                                        sendPartialResultsOnExpiry = sendPartialResultsOnExpiry,
                                                        expireGroupsUponCompletion = expireGroupsUponCompletion))
  }
  /**
   * 
   */
  def on(correlationFunction:Function1[_,AnyRef]) = new SendingEndpointComposition(null, new MessageAggregator())  {
    throw new UnsupportedOperationException("Currently this DSL element is not supported. Support will be added in version 1.0.0.M2")
    def where(name:String = "$aggr_" + UUID.randomUUID().toString.substring(0, 8),
              keepReleasedMessages:Boolean = false,
              messageStore:MessageStore = new SimpleMessageStore,
              sendPartialResultsOnExpiry:Boolean = true,
              expireGroupsUponCompletion:Boolean = false) =
      new SendingEndpointComposition(null, new MessageAggregator(name = name,
                                                        keepReleasedMessages = keepReleasedMessages,
                                                        messageStore = messageStore,
                                                        sendPartialResultsOnExpiry = sendPartialResultsOnExpiry,
                                                        expireGroupsUponCompletion = expireGroupsUponCompletion))

    def until(releaseFunction:Function1[_,Boolean]) = new SendingEndpointComposition(null, new MessageAggregator())  {
      def where(name:String = "$aggr_" + UUID.randomUUID().toString.substring(0, 8),
              keepReleasedMessages:Boolean = false,
              messageStore:MessageStore = new SimpleMessageStore,
              sendPartialResultsOnExpiry:Boolean = true,
              expireGroupsUponCompletion:Boolean = false) =
        new SendingEndpointComposition(null, new MessageAggregator(name = name,
                                                          keepReleasedMessages = keepReleasedMessages,
                                                          messageStore = messageStore,
                                                          sendPartialResultsOnExpiry = sendPartialResultsOnExpiry,
                                                          expireGroupsUponCompletion = expireGroupsUponCompletion))
    }
  }
  /**
   * 
   */
  def on(correlationKey:AnyRef) = new SendingEndpointComposition(null, new MessageAggregator())  {
    throw new UnsupportedOperationException("Currently this DSL element is not supported. Support will be added in version 1.0.0.M2")
    def where(name:String = "$aggr_" + UUID.randomUUID().toString.substring(0, 8),
              keepReleasedMessages:Boolean = false,
              messageStore:MessageStore = new SimpleMessageStore,
              sendPartialResultsOnExpiry:Boolean = true,
              expireGroupsUponCompletion:Boolean = false) =
      new SendingEndpointComposition(null, new MessageAggregator(name = name,
                                                        keepReleasedMessages = keepReleasedMessages,
                                                        messageStore = messageStore,
                                                        sendPartialResultsOnExpiry = sendPartialResultsOnExpiry,
                                                        expireGroupsUponCompletion = expireGroupsUponCompletion))

    def until(releaseFunction:Function1[_,Boolean]) = new SendingEndpointComposition(null, new MessageAggregator())  {
      def where(name:String = "$aggr_" + UUID.randomUUID().toString.substring(0, 8),
              keepReleasedMessages:Boolean = false,
              messageStore:MessageStore = new SimpleMessageStore,
              sendPartialResultsOnExpiry:Boolean = true,
              expireGroupsUponCompletion:Boolean = false) =
        new SendingEndpointComposition(null, new MessageAggregator(name = name,
                                                          keepReleasedMessages = keepReleasedMessages,
                                                          messageStore = messageStore,
                                                          sendPartialResultsOnExpiry = sendPartialResultsOnExpiry,
                                                           expireGroupsUponCompletion = expireGroupsUponCompletion))
    }
  }
  /**
   *  
   */
  def until(releaseFunction:Function1[_,Boolean]) = new SendingEndpointComposition(null, new MessageAggregator()) {
    throw new UnsupportedOperationException("Currently this DSL element is not supported. Support will be added in version 1.0.0.M2")
    def where(name:String = "$aggr_" + UUID.randomUUID().toString.substring(0, 8),
              keepReleasedMessages:Boolean = false,
              messageStore:MessageStore = new SimpleMessageStore,
              sendPartialResultsOnExpiry:Boolean = true,
              expireGroupsUponCompletion:Boolean = false) =
      new SendingEndpointComposition(null, new MessageAggregator(name = name,
                                                        keepReleasedMessages = keepReleasedMessages,
                                                        messageStore = messageStore,
                                                        sendPartialResultsOnExpiry = sendPartialResultsOnExpiry,
                                                        expireGroupsUponCompletion = expireGroupsUponCompletion))
    
    def on(correlationKey:AnyRef) = new SendingEndpointComposition(null, new MessageAggregator())  {
    	def where(name:String = "$aggr_" + UUID.randomUUID().toString.substring(0, 8),
              keepReleasedMessages:Boolean = false,
              messageStore:MessageStore = new SimpleMessageStore,
              sendPartialResultsOnExpiry:Boolean = true,
              expireGroupsUponCompletion:Boolean = false) =
              new SendingEndpointComposition(null, new MessageAggregator(name = name,
                                                        keepReleasedMessages = keepReleasedMessages,
                                                        messageStore = messageStore,
                                                        sendPartialResultsOnExpiry = sendPartialResultsOnExpiry,
                                                        expireGroupsUponCompletion = expireGroupsUponCompletion))
    }
  }

  def where(name:String = "$aggr_" + UUID.randomUUID().toString.substring(0, 8),
            keepReleasedMessages:Boolean = true,
            messageStore:MessageStore = new SimpleMessageStore,
            sendPartialResultsOnExpiry:Boolean = false,
            expireGroupsUponCompletion:Boolean = false) =
    new SendingEndpointComposition(null, new MessageAggregator(name = name,
                                                      keepReleasedMessages = keepReleasedMessages,
                                                      messageStore = messageStore,
                                                      sendPartialResultsOnExpiry = sendPartialResultsOnExpiry,
                                                      expireGroupsUponCompletion = expireGroupsUponCompletion))
}

private[dsl] class MessageAggregator(name:String = "$ag_" + UUID.randomUUID().toString.substring(0, 8),
                                          val keepReleasedMessages:Boolean = true,
                                          val messageStore:MessageStore = new SimpleMessageStore,
                                          val sendPartialResultsOnExpiry:Boolean = false,
                                          val expireGroupsUponCompletion:Boolean = false) extends IntegrationComponent(name)

