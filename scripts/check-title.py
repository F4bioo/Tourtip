import re

def main():
    with open('.github/markdown/check-title-accepted-suffixes.md', 'r') as file:
        accepted_suffixes = file.read().strip().split('\n')

    formatted_suffixes = '- ' + '\n- '.join(accepted_suffixes)

    with open('.github/markdown/check-title-error-message-tag.md', 'r') as file:
        error_message_content = file.read()

    updated_content = re.sub(r'\$ACCEPTED_SUFFIXES', formatted_suffixes, error_message_content)

    with open('.github/markdown/formatted-check-title-error-message-tag.md', 'w') as file:
        file.write(updated_content)

if __name__ == "__main__":
    main()
