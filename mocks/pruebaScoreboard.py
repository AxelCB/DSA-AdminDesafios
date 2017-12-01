import requests
import sys
import json

def main(argv):
    token = "BearereyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUxMjk1NTIyNX0.QfD5nGTe_p5EjA6ZBpveU8zxKSHPSkSZB747fm3ELBzBOtkjcKzHskWflB_Bt90UYs8MbQn-9dkDQ8mH36sQXQ"
    if(len(argv) > 0):
        token = argv[0]
    headers = {
        "Authorization" : token
    }
    player_id = 5
    url = "http://localhost:8080/api/players/%s/team-score" % player_id
    response = requests.get(url, headers=headers)
    print json.dumps(response.json(), indent=2)


if __name__ == "__main__":
    main(sys.argv[1:])
