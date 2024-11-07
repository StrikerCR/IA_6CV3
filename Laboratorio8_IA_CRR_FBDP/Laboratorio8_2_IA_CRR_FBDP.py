import pandas as pd

# Cargar el archivo bezdekIris.data
file_path = 'bezdekIris.data'  # Asegúrate de que este archivo esté en el mismo directorio o usa la ruta completa

# Leer el archivo y almacenarlo en un DataFrame
column_names = ['sepal_length', 'sepal_width', 'petal_length', 'petal_width', 'species']  # Nombres de las columnas
df = pd.read_csv(file_path, header=None, names=column_names)

# Imprimir el DataFrame
print(df)
