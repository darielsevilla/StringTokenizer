# Instrucciones de ejecución word tokenizer

## Prerequerimientos 
### prerequisitos generales
-Para la ejecución del proyecto se debe descargar en el ambiente de UBUNTU, o LINUX
-debe tener instalado y configurado HADOOP en su UBUNTU o LINUX, preferiblemente la versión 3.4.0
-Dabe tener instalado java, version 11


### dataset
-se debe descargar el dataset del siguiente enlace: https://www.kaggle.com/datasets/andrewmvd/steam-reviews
-debe almacenar el dataset en la carpeta dataFiles del proyecto, bajo el nombre "dataset.csv"

## Inicializar hadoop
-debe ir a la carpeta hadoop-3.4.0/sbin. esta carpeta usualmente se encuentra en la carpeta del usuario por lo que el comando adecuado en la mayoria de los casos seria:

    cd <user_name>/hadoop-3.4.0/sbin

-debe ejecutar los siguientes comandos:

    sudo service ssh start
    ./start-dfs.sh 
    ./start-yarn.sh

-para verificar si se inicializó bien, puede ejecutar el comando "jps". El resultado deberia ser el siguiente:

    1616 Jps
1   248 NodeManager
    516 NameNode
    855 SecondaryNameNode
    1112 ResourceManager
    652 DataNode

### crear directory en hdfs
-se recomienda crear una directory en hdfs para almacenar los datos relacionados el proyecto. se puede realizar de la siguiente manera, con el nombre siendo dataProyecto:

    hdfs dfs -mkdir /dataProyecto

-Esto no es estrictamente necesario, pero por el resto de la guia se hara referencia a esta dirección a la hora de usar hadoop.

## Ejecución del proyecto
El proyecto esta dividido en 2 programas, el StringTokenizer, que contiene el preprocesamiento y el Frequency Analysis, conectados por un main y el word count.

### ejecución de preprocesamiendo
-se debe ir a la carpeta raiz del proyecto. Asumiendo que almacenó el proyecto en la carpeta de su usuario, el comando indicado seria:

    cd StringTokenizer

-luego para ejecutar el pre procesamiento, se ejecuta el siguiente comando en la carpeta:

    java stringtokenizer.StringTokenizer 1

Esto ejecutará un preprocesamiento del dataset, y lo almacenará en un archivo llamado "processedDS.csv", en la carpeta dataFiles,

### ejecución de word count

-para esto, deberá subir el dataset preprocesado a hadoop. Usando el siguiente comando, se almacenará en la dirección "/dataProyecto".

    hdfs dfs -put -f ./dataFiles/processedDS.csv /dataProyecto/processedDS.csv

-luego, deberá moverse a la siguiente carpeta src/wordcount con el siguiente comando:

    cd src/wordcount

para ejecutar el wordcount de una palabra, se usa el siguiente comando:

    hadoop jar wordcountsingle.jar WordCount /dataProyecto/processedDS.csv /dataProyecto/results

para ejecutar el de dos palabras, se usa el siguiente comando:

    hadoop jar wordcountdouble.jar WordCount2 /dataProyecto/processedDS.csv /dataProyecto/resultsdouble

estos comandos realizan el wordcount, y lo almacenan en las direcciones /dataProyecto/results y /dataProyecto/resultsdouble respectivamente.

-Como estos comandos requieren que la dirección no exista, si se quieren ejecutar más de una vez, se deben eliminar usando los siguientes comandos:

    hdfs dfs -rm -r /dataProyecto/results

    hdfs dfs -rm -r /dataProyecto/resultsdouble

-luego, se debe regresar a la carpeta raiz del proyecto ejecutando 2 veces el comando:

    cd .. 

-para recuperar los archivos procesados y almacenarlos en la carpeta dataFiles, se hace lo siguiente:

    hdfs dfs -get -f /dataProyecto/results ./dataFiles

    hdfs dfs -get -f /dataProyecto/resultsdouble ./dataFiles


### ejecución de frequency analysis

    Para ejecutar el frequency analysis, debe estar en la carpeta raiz del proyecto. Puede ejecutar los siguientes comandos, que funcionan con el formato:

        java stringtokenizer.StringTokenizer  2 <palabras_unicas_o_dobles> <num_de_palabras_en_el_top>
    
    
    -si quiere conseguir las top 10 palabras unicas:

        java stringtokenizer.StringTokenizer  2 1 10
    
    -si quiere conseguir las top 20 palabras unicas:

        java stringtokenizer.StringTokenizer  2 1 20
    
    -si quiere conseguir las top 10 pares de palabras:

        java stringtokenizer.StringTokenizer  2 2 10
    
    -si quire conseguir las top 20 pares de palabras:

        java stringtokenizer.StringTokenizer  2 2 20


## para finalizar la ejecución de hadoop, una vez terminado de usar el programa:

    se debe regresar a la carpeta hadoop-3.4.0/sbin, y ejecutar los siguientes comandos:

        ./stop-dfs.sh
        
        ./stop-yarn.sh

        sudo service ssh stop