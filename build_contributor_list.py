import requests
import json

def fetch_contributors(owner, repo):
    """
    Fetch the list of contributors for a GitHub repository.

    Args:
        owner (str): GitHub username or organization name that owns the repository.
        repo (str): Name of the repository.

    Returns:
        list: A list of contributors with their GitHub username, avatar URL, and contributions count.
    """
    url = f"https://api.github.com/repos/{owner}/{repo}/contributors"
    headers = {
        "Accept": "application/vnd.github.v3+json"
    }

    try:
        response = requests.get(url, headers=headers)
        response.raise_for_status()  # Raise an error for bad status codes
        contributors = response.json()

        contributor_list = [
            {
                "username": contributor["login"],
                "avatar_url": contributor["avatar_url"],
                "contributions": contributor["contributions"]
            }
            for contributor in contributors
        ]
        return contributor_list

    except requests.exceptions.RequestException as e:
        print(f"An error occurred: {e}")
        return []

def save_contributors_to_file(contributors, filename="contributors.json"):
    """
    Save the contributors list to a JSON file.

    Args:
        contributors (list): List of contributor dictionaries.
        filename (str): Name of the output JSON file.
    """
    with open(filename, "w") as file:
        json.dump(contributors, file, indent=4)
    print(f"Contributor data saved to {filename}")

# Example usage:
owner = "aniruddha-adhikary"
repo = "mrt-buddy"
contributors = fetch_contributors(owner, repo)

# Save contributors to JSON file
save_contributors_to_file(contributors)