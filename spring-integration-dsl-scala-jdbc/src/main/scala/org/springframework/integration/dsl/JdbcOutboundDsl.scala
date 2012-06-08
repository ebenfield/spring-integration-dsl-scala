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
import java.util.UUID
import org.w3c.dom.Element
import org.w3c.dom.Document
import java.io.File
import javax.sql.DataSource

/**
 * @author Ewan Benfield
 */
private[dsl] class JdbcOutboundAdapterConfig(name: String = "$file_out_" + UUID.randomUUID().toString.substring(0, 8),
  target: String,
  dataSource: DataSource) extends SimpleEndpoint(name, target) with OutboundAdapterEndpoint {

/*<int-jdbc:outbound-channel-adapter
query="insert into foos (id, status, name) values (:headers[id], 0, :payload[foo])"
data-source="dataSource"
channel="input"/>*/

  override def build(document: Document = null,
    targetDefinitionFunction: Function1[Any, Tuple2[String, String]],
    compositionInitFunction: Function2[BaseIntegrationComposition, AbstractChannel, Unit] = null,
    inputChannel: AbstractChannel,
    outputChannel: AbstractChannel): Element = {
    require(inputChannel != null, "'inputChannel' must be provided")    // TODO

    val beansElement = document.getElementsByTagName("beans").item(0).asInstanceOf[Element]
    if (!beansElement.hasAttribute("xmlns:int-jdbc")){
      beansElement.setAttribute("xmlns:int-jdbc", "http://www.springframework.org/schema/integration/jdbc")
      val schemaLocation = beansElement.getAttribute("xsi:schemaLocation")
      beansElement.setAttribute("xsi:schemaLocation", schemaLocation + JdbcDsl.jdbcSchema);
    }

    val element = document.createElement("int-jdbc:outbound-channel-adapter")
    element.setAttribute("id", this.name)
    val dataSourceName = targetDefinitionFunction(Some(this.dataSource))._1
    element.setAttribute("data-source", dataSourceName)
    element.setAttribute("channel", inputChannel.name)
    element.setAttribute("query", target)
    element
  }

}
