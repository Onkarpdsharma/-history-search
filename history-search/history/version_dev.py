import json
import sys
'''
#Take specific GCP response, get the version number, and increment last number
version = json.loads("".join(sys.argv[1:]))[0]["tags"][0].split(".")
version[-1] = str(int(version[-1].replace('d',''))+1)
print(".".join(version))
'''
#Take specific GCP response, get the version number, and increment last number
version = json.loads("".join(sys.argv[1:]))[0]["tags"][0].split(".")
if 'p' in version[-1]:
    version[-1] = str(int(version[-1].replace('p', '')) + 1)+"p"
    print(".".join(version))
elif 'd' in version[-1]:
    version[-1] = str(int(version[-1].replace('d', '')) + 1)+"d"
    print(".".join(version))

