import requests
import sys

def main(argv):
    token = "BearereyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUxMjk1NTIyNX0.QfD5nGTe_p5EjA6ZBpveU8zxKSHPSkSZB747fm3ELBzBOtkjcKzHskWflB_Bt90UYs8MbQn-9dkDQ8mH36sQXQ"
    if(len(argv) > 0):
        token = argv[0]
    headers = {
        "Authorization" : token
    }
    game_id = 62
    url = "http://localhost:8080/api/configurations/game/%s" % game_id
    response = requests.post(url, headers=headers)
    print response.status_code


if __name__ == "__main__":
    main(sys.argv[1:])
