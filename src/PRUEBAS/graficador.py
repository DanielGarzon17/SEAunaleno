import math
import matplotlib.pyplot as plt
import matplotlib.ticker as ticker
import json
def generar_grafico(datos):
    x = [int(i) for i in range(len(datos))] 
    # Generar el gráfico
    plt.scatter(x,datos)
    plt.xlabel('Puntaje obtenido')
    plt.ylabel('Intento')
    plt.title('Gráfico')
    plt.savefig("src\RECURSOS\graphic.jpg")
    # plt.show()

def leer_archivo_json(nombre_archivo):
    datos=[]
    try:
        with open(nombre_archivo) as archivo:
            datos_json = json.load(archivo)
            datos = [float(dato) for dato in datos_json]
            return datos
    except IOError:
        print("Error al leer el archivo JSON.")
        return []

def generar_diagrama_pareto(datos):
    # Ordenar los datos en orden descendente
    datos_ordenados = sorted(datos, reverse=True)
    # print(datos_ordenados)

    # Calcular el número de rangos
    num_rangos = 5

    # Calcular los rangos y sus intervalos
    rango_intervalo = len(datos) / num_rangos
    rangos = []
    inicio = 0
    amplitud= (max(datos)-min(datos))/num_rangos
    for i in range(num_rangos):
        fin = inicio + amplitud
        rangos.append((inicio, fin))
        inicio = fin
    

    # Calcular los datos para cada rango
    datos_rangos = []
    
    for rango in rangos:
        cont= 0
        inicio, fin = rango
        for i in datos_ordenados:
            if i>=inicio and i<fin:
                cont += 1
        datos_rangos.append(cont)
    # print(datos_rangos)
    # Calcular el porcentaje acumulado
    porcentaje_acumulado=[]    
    for i in range(len(datos_rangos)):
        aux=datos_rangos[i]/len(datos)*100
        if(i==0):
            porcentaje_acumulado.append(aux)
        else:
            porcentaje_acumulado.append(porcentaje_acumulado[i-1]+aux)
    # Crear el gráfico de barras para los datos
    categorias = [f'[{int(rango[0])} , {int(rango[1])}]' for rango in rangos]
    fig, ax1 = plt.subplots()
    ax1.bar(categorias, datos_rangos, color='tab:blue')
    ax1.set_xlabel('Puntajes obtenidos')
    ax1.set_ylabel('Frecuencia', color='tab:blue')
    ax1.tick_params(axis='y', labelcolor='tab:blue')
    # # Formatear el eje y con separadores de miles
    formatter = ticker.FuncFormatter(lambda x, pos: '{:,.0f}'.format(x))
    ax1.yaxis.set_major_formatter(formatter)

    # Crear el gráfico de línea para el porcentaje acumulado
    ax2 = ax1.twinx()
    ax2.plot(categorias, porcentaje_acumulado[:num_rangos], color='tab:red', marker='o')
    ax2.set_ylabel('Porcentaje acumulado (%)', color='tab:red')
    ax2.tick_params(axis='y', labelcolor='tab:red')

    # Ajustar los espacios entre las barras
    plt.subplots_adjust(bottom=0.15)

    # Rotar las etiquetas del eje x en 45 grados
    plt.xticks(rotation=45)

    # Mostrar el gráfico
    plt.title('Diagrama de Pareto')
    plt.savefig("src\RECURSOS\pareto-graphic.jpg")

if __name__ == '__main__':
    data = leer_archivo_json("src\PRUEBAS\data.json")
    # Generar el gráfico
    generar_grafico(data)
    generar_diagrama_pareto(data)
    
