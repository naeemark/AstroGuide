from flask import Flask
from flask import request

app = Flask(__name__)


@app.route('/api', methods=['GET', 'POST'])
def api():
    if request.method == 'GET':
        try:
            email = request.args.get("email")
            with open(email+'.txt', 'r') as f:
                return f.read()
        except:
            return 'error'

    elif request.method == 'POST':
        try:
            email = request.form.get('email')
            data = request.form.get('data')
            print(data)
            with open(email+'.txt', 'w') as f:
                f.write(data)
            return 'True'
        except:
            return 'False'


if __name__ == '__main__':
    app.run()
