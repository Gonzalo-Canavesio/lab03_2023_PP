import itertools
import re


def remove_types(string):
    pattern = r'\b\w+\s+([A-Za-z]+)\b'
    cleaned_string = re.sub(pattern, r'\1', string)

    return cleaned_string


def generate_java_classes(classes, interfaces, output_file):
    combinations = list(itertools.product(classes, interfaces))

    with open(output_file, 'w') as file:
        file.write("package namedEntity;\n\n")
        for class_name, interface_name in combinations:
            new_class = class_name + interface_name.title()
            args = classes[class_name]
            template = f"""
class {new_class} extends {class_name} implements {interface_name}{{
        {new_class}({args}){{
            super({remove_types(args)});
        }}
}}

            """
            file.write(template)


if __name__ == "__main__":
    class_list = {
        "apellido": "String name, String category, int frequency, int id, String origen",
        "nombre": "String name, String category, int frequency, int id, String origen, String formasAlt",
        "pais": "String name, String category, int frequency, int id, int poblacion, String lenguaOficial",
        "ciudad": "String name, String category, int frequency, int id, pais pais, String capital, int poblacion",
    }

    interface_list = [
        "Futbol",
        "Cine",
        "Nacional",
    ]

    output_filename = "EntidadesConTema.java"
    generate_java_classes(class_list, interface_list, output_filename)
