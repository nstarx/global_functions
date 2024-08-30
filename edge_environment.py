import datetime
import os
import uuid



def edge(uri):

    PZ_NAME = os.getenv("PZ_NAME", "NOT_SET")
    REGION = os.getenv("REGION", "NOT_SET")
    NS = os.getenv("NS", "NOT_SET")

    ROOT_URI = f"intelligence-edge-{PZ_NAME}-{REGION}-{NS}"

    now = datetime.datetime.now()
    current_date = now.strftime("D_%Y-%m-%d")
    current_time = now.strftime("T_%H-%M-%S")
    unique_id = str(uuid.uuid4())

    uri = uri.replace("$ROOT", ROOT_URI)
    uri = uri.replace("$DATE", current_date)
    uri = uri.replace("$TIME", current_time)
    uri = uri.replace("$UUID", unique_id)

    return uri


if __name__ == "__main__":

    os.environ["PZ_NAME"] = "test"
    os.environ["REGION"] = "us-west-2"
    os.environ["NS"] = "dev"

    sample = edge(f"s3://$ROOT/Feast_features_spaces_comparison/datasets/$DATE/$TIME/$UUID/")
    print(sample)
