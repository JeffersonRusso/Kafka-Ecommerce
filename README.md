# Kafka-Ecommerce

Projeto desenvolvido na formação KAFKA da Alura.

Para fazer funcionar o Kafka-Ecommerce é necessario ter o KAFKA no computador e rodar os seguintes comandos:

## ININICIAR ZOOKEEPER
bin/zookeeper-server-start.sh config/zookeeper.properties

## INICIAR KAFKA
bin/kafka-server-start.sh config/server.properties 

Demais comandos:

## LISTA TOPICOS
bin/kafka-topics.sh --list --bootstrap-server localhost:9092

## CRIAR NOVO TOPICO 
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic LOJA_NOVO_PEDIDO

## PRODUZ MENSAGENS PARA O TOPICO
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic LOJA_NOVO_PEDIDO

## CONSUMIR UMA MENSAGEM DO INICIO
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic LOJA_NOVO_PEDIDO --from-beginning
