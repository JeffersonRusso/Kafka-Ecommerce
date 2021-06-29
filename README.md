# Kafka-Ecommerce

Projeto desenvolvido na formação KAFKA da Alura.

Para fazer funcionar o Kafka-Ecommerce é necessario ter o KAFKA no computador e rodar os seguintes comandos:

## INICIAR ZOOKEEPER
bin/zookeeper-server-start.sh config/zookeeper.properties

## INICIAR KAFKA
bin/kafka-server-start.sh config/server.properties 

Demais comandos:

## LISTAR TOPICOS
bin/kafka-topics.sh --list --bootstrap-server localhost:9092

## CRIAR NOVO TOPICO 
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic ECOMMERCE_NEW_ORDER

## PRODUZIR MENSAGEM PARA O TOPICO
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic LOJA_NOVO_PEDIDO

## CONSUMIR UMA MENSAGEM DO INICIO
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic LOJA_NOVO_PEDIDO --from-beginning

## ALTERA O TOPICO PARA 3 PARTIÇÕES
bin/kafka-topics.sh -alter --zookeeper localhost:2181 --topic ECOMMERCE_NEW_ORDER --partitions 3

## DESCREVER GRUPOS DE CONSUMO
bin/kafka-consumer-groups.sh --all-groups --bootstrap-server localhost:9092 --describe

## CONFIGURAÇÃO DO SERVER ( abrir com o editor de texto VI)
vi config/server.properties
/partit

## DESCREVER TODOS OS TOPICOS
bin/kafka-topics.sh --bootstrap-server localhost:9092 --describe
