# Git Beginner to Expert Notes

Git is one of the most important tools in software development. In automation projects, Git helps you track, review, and collaborate on code safely. In interviews, you are expected to understand not only commands but also when and why to use them.

## 1. Git basics

### What it is
Git is a distributed version control system. It tracks file changes over time and lets multiple people work on the same project without overwriting each other's work.

### Why it matters
Without Git, it is difficult to manage code versions, review work, recover from mistakes, or collaborate in a team.

### Common terms
- Repository: project folder tracked by Git
- Commit: a saved snapshot of your project
- Branch: an independent line of work
- Merge: combine one branch into another
- Pull: fetch remote changes and update your branch
- Push: upload your local commits to GitHub or another remote server
- Clone: copy a remote repository to your machine
- Conflict: when two developers edit the same code in the same place

## 2. Initial setup

### Why and when
Run these when you install Git for the first time or set up a new machine.

```bash
git --version
git config --global user.name "Your Name"
git config --global user.email "you@example.com"
git config --global --list
```

### What this does
- identifies the Git version
- stores your identity for all commits
- verifies the settings are correct

## 3. Basic project workflow

### When to use
Use this when starting a new project or initializing a repository locally.

```bash
git init
git status
git add .
git commit -m "Initial commit"
```

### Why it matters
This creates a Git repository, stages files, and saves the first version of the project.

## 4. Daily workflow

### When to use
Use this every time you change code, add a feature, or fix a bug.

```bash
git status
git add <file>
git add .
git commit -m "Add feature or fix"
git push origin main
git pull origin main
```

### What each command does
- `git status`: shows changed files
- `git add`: stages changes for commit
- `git commit`: creates a saved version
- `git push`: uploads code to remote repository
- `git pull`: gets latest changes before or after you work

### Why this matters
This is the basic cycle of collaborative development.

## 5. Branching

### What it is
A branch allows you to work separately from the main code without breaking the main development line.

### When to use it
Use a branch for a new feature, bug fix, or experiment.

```bash
git branch
git branch feature-login
git checkout feature-login
# or
git switch feature-login

# create and switch in one command
git checkout -b feature-login
```

### Why it matters
Branches keep work isolated and reduce conflicts.

## 6. Merge and rebase

### Merge
Use `merge` when you want to combine work from one branch into another while preserving history.

```bash
git checkout main
git merge feature-login
```

### Rebase
Use `rebase` when you want a cleaner linear history.

```bash
git checkout feature-login
git rebase main
```

### Why choose one over the other?
- Merge is safer for shared branches
- Rebase creates a cleaner history but can rewrite branch history, so use carefully

### Merge conflict
A conflict happens when both branches changed the same lines in the same file.

```bash
git status
git add <resolved-file>
git commit -m "Resolve merge conflict"
```

## 7. Remote repository commands

### When to use
Use this when connecting your local repo to GitHub or another remote server.

```bash
git remote -v
git remote add origin https://github.com/username/repository.git
git remote set-url origin <new-url>
git fetch origin
git pull origin main
git push origin main
```

### Why it matters
These commands sync your local repository with the remote server.

## 8. Useful command set

```bash
git log
git log --oneline
git log --oneline --decorate --graph --all
git diff
git diff --staged
git stash
git stash list
git stash pop
git revert <commit-sha>
git reset --soft HEAD~1
git reset --mixed HEAD~1
git reset --hard HEAD~1
git restore --staged <file>
git restore <file>
```

### What they are used for
- `git log`: view commit history
- `git diff`: compare changes
- `git stash`: temporarily save your work
- `git revert`: undo a commit safely
- `git reset`: move branch pointer back, with different levels of change retention
- `git restore`: restore a file from the last commit or from staging

## 9. Tags

### When to use
Use tags for release versions such as `v1.0`, `v2.0`, or a checkpoint before deployment.

```bash
git tag
git tag v1.0.0
git push origin v1.0.0
```

### Why it matters
Tags help identify important versions quickly.

## 10. .gitignore

### What it is
`.gitignore` tells Git which files or folders to ignore and not track.

### Why it matters
This prevents noisy files such as build output, IDE config, logs, and temporary data from being committed.

```gitignore
target/
*.class
*.log
.idea/
.vscode/
.DS_Store
```

## 11. Team workflow example

```bash
git checkout main
git pull origin main
git checkout -b feature/selenium-login
git add .
git commit -m "Add Selenium login automation"
git push -u origin feature/selenium-login
```

### What this does
- starts from the latest main branch
- creates a feature branch
- commits the work
- pushes it to remote for review

Then open a pull request and merge after review.

## 12. Interview-focused Git questions

### Q: Difference between `git fetch` and `git pull`?
- `git fetch` downloads updates without merging them.
- `git pull` fetches and merges or rebases automatically.

### Q: What is a merge conflict?
A merge conflict happens when both branches change the same lines in the same file differently.

### Q: Why use branches?
Branches isolate work and reduce risk to the main branch.

### Q: What is `.gitignore`?
It is a file that tells Git which files and folders should not be tracked.

### Q: What is `stash`?
It temporarily stores local changes so you can switch branches or clean your working state.

### Q: What is `revert`?
It creates a new commit that undoes an older commit without rewriting history.

### Q: What is rebase?
It replays commits on top of a different base branch to produce a cleaner linear history.

## 13. Best practices

- Commit often with meaningful messages
- Keep commits small and focused
- Use feature branches for separate work
- Pull before pushing
- Review pull requests carefully
- Never commit secrets or credentials
- Keep the main branch stable
- Use `.gitignore` to reduce noise

## 14. Quick cheat sheet

```bash
git status
git add .
git commit -m "Your message"
git push origin main
git pull origin main
git checkout -b feature/my-change
git merge feature/my-change
git stash
git stash pop
git revert <commit-sha>
```

## 15. Final takeaway

Git is not just about code storage. It is about safe collaboration, version tracking, release management, and avoiding mistakes in team projects. In interviews, they often want to hear the reason behind the command, not only the syntax.
